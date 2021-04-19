# ps-spring-data-jpa
This repository will be a starting point and aid for those taking the Spring Framework: Spring Data JPA course on Pluralsight

### Module2
* what need be setup to get JPA working
  - dependency
  - JPA entities
  - repositories
* Spring Data JPA
  - Enhances JPA
  - Simplifies Data Tier
  - Query DSL (Domain Specific Language)
  - Data Tier: Auditing, paging
  - Easy to extend
* Basic JPA
  - @Entity, @Table, @Id, @GeneratedValue, @Column, @ManyToMany, @JoinTable, @JoinColumn
* Spring Data 
  - Spring Data Commons: Repository, CRUD and Query Generation
* Only data depedency is spring-boot-starter-data-jpa
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```
### Module3
* Test class can be annotated with @SpringBootTest
  * @DataJpaTest calls for less resources than @SpringBootTest. However, by default @DataJpaTest replaces the database with an embedded one. An annotation needs to be added as below if embedded database is not used
  ```
  @DataJpaTest
  @AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
  ```
* @Repository
	- Persistence Contract
	- When switching out persistence tier the service tier should not be impacted
	- DAO pattern
* Tiers: controller, service, persistence and database
* JPA Repository
	- Java interface, not class
	- Map 1 to 1 with a JPA entity
	- Focus on DAO contract
	- Spring automatically generate the contract
	- Create changes: saveAndFlush
	- Find changes getOne
	- Delete changes: deleteById
* Main features of JPA Repository
  - Query DSL: Repository
  - CRUD operations: CrudRepository
  - Paging and sorting: PagingAndSortingRepository
  - Helpers
    - count(): CrudRepository
    - existsById(ID): CrudRepository
    - flush: JpaRepository
    - deleteInBatch(Iterable): JpaRepository
   - Repository, CrudRepository and PagingAndSortingRepository comes from Spring Data project
   - JpaRepository comes from Spring Data JPA
* Best Practice
  - use a baseEntity for all the auditing features
  - extend a custom repository that extends JpaRepository
  - When refactor existing repositories
    - make sure you have a complete TestSuite
    - Name MyRepository to MyJpaRepository
    - Switch client code dependencies on the repository if there are not many client bindings
    - If there are many places to change, leave the current repository as a Proxy
* When Spring Boot starts Spring Data JPA looks at the class path to find any type that has JpaRepository and treat it as @Repository
### Module 4
* Advantages of Query DSL
  * Recovering time spend on your data model
  * Reduced codebase
  * Query Validity
* DSL (Domain Specific Language) is a customized extension of a software programming language that addresses a specific business or domain
* Query DSL Basics
  * Query DSL = Method Contracts
  * Query DSL can begin with
    * findBy, queryBy, readBy, countBy, getBy
  * Query DSL uses JPA entity attribute names for criteria: 
  * Multiple criteria combined with ["And", "Or"]
    * Contains => like
  * return types
    * List: when multiple instances
    * Entity: when only one instance
    * Long: count value
  * When running Query DSL test, the generated SQL Query started with "Hibernate: "
* Query DSL keywords
  * AND - OR: combines multiple criteria query filters together using a conditional And or Or
    * findByFirstNameAndLastName
      * where a.firstName=?1 and a.lastName=?2
    * findByFirstNameOrLastName
      * where a.firstName= ?1 or a.lastName= ?2
  * EQUALS - IS - NOT: The default '=' when comparing the criteria with the filter value. Use Not when wanting to compare not equals
    * findBySessionLength
      * where a.sessionLength = ?1
    * findBySessionLengthIs
      * where a.sessionLength = ?1
    * findBySessionLengthEquals
      * where a.sessionLength = ?1
    * findBySessionLengthNot
      * where a.sessionLength != ?1
  * LIKE - NOT LIKE: Useful when trying to match, or not match, a portion of the criteria filter value. Need add "%" 
    * findBySessionNameLike("Java%")
      * where a.sessionName like ?1
    * findBySessionnameNotLike("Python%")
      * where a.sessionName not like ?1
  * STARTING/ENDING WITH - CONTAINING: Similar to the "Like" keyword except the % is automatically added to the filter value
    * Other variations: StartsWith, EndsWith, Contains, IsStartingWith, IsEndingWith, IsContaining, NotContaining, NotContains
    * findBySessionNameStartingWith("j")
      * where a.sessionName like ?1
    * findBySessionNameEndingWith("j")
      * where a.sessionName like ?1
    * findBySessionNameContaining("j")
      * where a.sessionName like ?1
  * LESS THAN - GREATER THAN: When you need to perform a <, <=, >, or >= comparision with number data types
    * findBySessionLengthLessThan(30)
      * where a.sessionLength < ?1
    * findBySessionLengthLessThanEqual(30)
      * where a.sessionLength <= ?1
    * findBySessionLengthGreaterThan(30)
      * where a.sessionLength > ?1
    * findBySessionLengthGreaterThanEqual(30)
      * where a.sessionLength >= ?1
  * BEFORE - AFTER - BETWEEN: When you need to perform a less than, greater than or range comparison with date/time data types
    * findByStartDateBefore(startDate);
      * where a.startDate < ?1
    * findByStartDateAfter(startDate);
      * where a.startDate > ?1
    * findByStartDateBetween
      * where a.startDate between ?1 and ?2
  * TRUE - FALSE: Userful when comparing boolean values with true or false
    * findByIncludesWorkshopTrue
      * where a.includesWorkshop = true
    * findByIncludesWorkshopFalse
      * where a.includesWorkshop = false
  * NULL - NOT NULL: Used to check whether a criteria value is null or not null
    * findBySpeakerPhotoNull()
      * where a.speakerPhoto is null
    * findBySpeakerPhotoIsNull()
      * where a.speakerPhoto is null
    * findBySpeakerPhotoNotNull()
      * where a.speakerPhoto not null
    * findBySpeakerPhotoIsNotNull()
      * where a.speakerPhoto not null
  * IN - NOT IN: When you need to test if a column value is part of a collection or set of values or not
    * findByCompanyIn(companies)
      * where a.company in ?1
    * findByCompanyNotIn(companies)
      * where a.company not in ?1
  * IGNORE CASE: When you need to perform a case insensitive comparison
    * findByCompanyIgnoreCase(cmpy)
      * where UPPER(a.company) = UPPER(?1)
    * findByCompanyContainsIgnoreCase(cmpy)
      * where UPPER(a.company) = UPPER(%?1%)
  * ORDER BY: Used to setup an order by clause on your query
    * findByLastNameOrderByFirstNameAsc(name)
      * where a.lastName = ?1 order by a.firstName asc
    * findByLastNameOrderByFirstNameDesc(name)
      * where a.lastName = ?1 order by a.firstName desc
  * FIRST - TOP - DISTINCT: Used to limit the results returned by the query
    * findFirstByFirstName(name);
      * where a.firstName = ?1 limit 1
    * findTop5ByFirstName(name);
      * where a.firstName = ?1 limit 5
    * findDistinctByFirst(name);
      * Select distinct where a.firstName =?1

### Module 5
* @Query
  * Spring Data JPA will not parse the method as a Query DSL method and generate JPQL. JPQL is within the annotation
  * The resulting Query will consider the Query and the Entity model
  * Reason to use
    * Reuse existing JPQL
    * Advanced queries
    * Eager loading control
  * @Query Options
    * Named Parameters
    ```
    @Query ("select tp from TicketPrice tp where tp.basePrice < :maxprice " +
    "and tp.ticketType.incudesWorkshop = true")
    List<TicketPrice> getTicketsUnderPriceWithWorkshops(@Param("maxprice") BigDecimal maxPrice);
    ```
    * Enhanced JPQL Syntax
    ```
    @Query("select s from Session s where s.sessionName like %?1")
    List<Session> getSessionsWithName(String name);
    ```
    * Native SQL Queries
    ```
    @Query(value = "select * from sessions s where s.session_name=?0", nativeQuery=true)
    List<Sessiobn> getSessionsWithName(String name);
    ```
    * Modifiable Queries
    ```
    @Modifying
    @Query("update Session s sets.sessionName= ?1")
    int updateSessionName(Strinbg name)
    ```
* @NamedQueries
  * Queries validated at app startup
  ```
  @Entity
  @NamedQuery(
  	name = "TicketPrice.namedFindTicketsByPricingCategoryName",
	query = "select tp from TicketPrice tp where tp.priceingCategory.pricinbgCategoryName = :name"
	)
   public class TicketPrice{...}
   ```
   ```
   public interface TicketPriceJpaRepository extends JpaRepository<TicketPrice, Long> {
   	List<TicketPrice> namedFindTicketsByPricingCategoryName(@Param("name") String name);
   ```
   ```
   @Query(name="TicketPrice.namedFindTicketsByPricingCategoryName")
   List<TicketPrice> getTicketsByPricingCategoryName(@Param("name") String name);
   ```
* @NamedNativeQuery
```
@NamedNativeQuery(
        name = "TicketPrice.nativeFindTicketsByCategoryWithWorkshop",
        query = "select tp.* from ticket_prices tp " +
                "left join ticket_types tt on tp.ticket_type_code = tt.ticket_type_code " +
                "left join pricing_categories pc on tp.pricing_category_code = pc.pricing_category_code " +
                "where tt.includes_workshop = true and pc.pricing_category_name = :name",
        resultClass = TicketPrice.class
)
public class TicketPrice{..}
```
* Where Should I put my queries
  * JPA Entities
    * @NamedQuery
    * @NamedNativeQuery
  * Repositories
    * @NamedQuery
    * @NamedNativeQuery
    * @Query
    * Query DSL  
  * SQL
  * JPQL
  * Query DSL
* JpaRepository Query Precedence
  * Methods with @Query annotation take highest precedence
  * Methods that match a named or native named query
  * Methods that follow the query DSL keyword naming structure

### Module 6
* Paging and Sorting
  * Classes: Page, Pagable, PageRequest, Sort
* Custom Repositories
  * Create a custom JpaRepository interface
  * Entity Jpa repository extends both JpaRepository and custom JpaRepository
  * custom JpaRepositoryImpl implements custom JpaRepository
* Auditing Support
  * Audit Annotations are placed on the Entity Class
    * @CreatedBy
    * @LastModifiedBy
    * @CreatedDate: Auto Set
    * @LastModifiedDate: Auto Set
    ```
    @Entity
    public class Model {
      @CreateBy
      private User user;
      
      @CreateDate
      private Datetime createdDate;
    }
   
  * SecurityAuditorAware implements AuditorAware
  ```
  public class SecurityAuditorAware implements AuditorAware<User> {
  public Optional<User> getCurrentAuditor() {
  	...
  	return user;
     }
  }
  ```
  
  * @EnableJpaAuditing and Auditing configuration
  ```
  @Configuration
  @EnableJpaAuditing
  public class AuditingConfiguration {
  	@Bean
	public AuditorAware<User> auditorProvider () {
	return new SecurityAuditorAware();
    }
  }
  ```
* Locking
  * Uses when there are transactions
  * User @Version
  ```
  @Entity
  public class Model {
  	@Version
	private int version;
  }
  ```
  * Optimistic Locking
    * If version nubmer doesn't match, throws OptimisticLockException
  * Pessimistic Locking
    * Long term locks teh data for the transaction duration, preventing others from accessing the data until the transaction commits
  * @Lock
  ```
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  List<Model> findByAttribureName(String name);
  ```



   
 


