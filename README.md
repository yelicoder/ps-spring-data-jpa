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
  - @Entity, @Id, @GeneratedValue, @Column, @ManyToMany, @JoinTable, @JoinColumn
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


