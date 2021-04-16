# ps-spring-data-jpa
This repository will be a starting point and aid for those taking the Spring Framework: Spring Data JPA course on Pluralsight

### Module2
* what need be setup to get JPA working
  - dependency
  - set up datasource , transaction manager etc in config file
  - JPA entities
  - set up persistence.xml file
  - repositories: create, update, delete
* Spring Data JPA
  - Enhances JPA
  - Simplifies Data Tier
  - JpaRepository
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
  * @DataJpaTest calls for less resources than @SpringBootTest. However, by default @DataJpaTest replace the database with an embedded one. An annotation need be added as below if embedded database is not used
  ```
  @DataJpaTest
  @AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
  ```
* @Repository
	- Persistence Contract
	- When switch out persistence tier the service tier should not be impacted
	- DAO pattern
* Tiers: controller, service, persistence and database
* JPA Repository
	- Java interface, not class
	- Map 1 to 1 with a JPA entity
	- Focus on DAO contract
	- Spring automatically generate the contract
	- Create changes to SaveandFlush
	- Find changes to getOne
	- Delete changes to DeleteById
* Main features of JPA Repository
  - Query DSL: Repository
  - CRUD operations: CrudRepository
  - Paging and sorting: PagingAndSortingRepository
  - Helpers
    - count(): CrudRepository
    - existsById(ID): CrudRepository
    - flush: JpaRepository
    - deleteInBatch(Iterable): JpaRepository
   - Repository, CrudRepository and PagingAndSortingRepository come from Spring Data project
   - JpaRepository comes from Spring Data JPA
* Best Practice
  - use a baseEntity for all the auditing features
  - extend a custom repository that extends JpaRepository
  - When refactor existing repositories
    - make sure you have a complete TestSuite
    - Naming MyRepository to MyJpaRepository
    - Switch client code dependencies on the repository if there are not many client bindings
    - If there are many places to change, leave the current repository as a Proxy
  - When Spring Boot starts Spring Data JPA looks at the class path to find any type that has JpaRepository and treat it as @Repository
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
  * EQUALS - IS - NOT: The default '=' when comparing the criteria with the filter value. Use Not when wanting to compare not equals
  * LIKE - NOT LIKE: Useful when trying to match, or not match, a portion of the criteria filter value. Need add "%" 
  * STARTING/ENDING WITH - CONTAINING: Similar to the "Like" keyword except the % is automatically added to the filter value
    * Other variations: StartsWith, EndsWith, Contains, IsStartingWith, IsEndingWith, IsContaining, NotContaining, NotContains
  * LESS THAN - GREATER THAN: When you need to perform a <, <=, >, or >= comparision with number data types
  * BEFORE - AFTER - BETWEEN: When you need to perform a less than, greater than or range comparison with date/time data types
  * TRUE - FALSE: Userful when comparing boolean values with true or false
    * findByIncludesWorkshopTrue
    * findByIncludesWorkshopFalse
  * NULL - NOT NULL: Used to check whether a criteria value is null or not null
    * findBySpeakerPhotoNull()
    * findBySpeakerPhotoIsNull()
    * findBySpeakerPhotoNotNull()
    * findBySpeakerPhotoIsNotNull()
  * IN - NOT IN: When you need to test if a column value is part of a collection or set of values or not
    * findByCompanyIn(companies)
    * findByCompanyNotIn(companies)


