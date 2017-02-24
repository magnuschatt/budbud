package chatt.budbud

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

val log: Logger = LoggerFactory.getLogger(App::class.java)

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}

@SpringBootApplication
class App {

    @Bean
    fun init(repository: CustomerRepository) = CommandLineRunner {
        log.info("Initializing app...")

        repository.deleteAll()

        // save a couple of customers
        repository.save(Customer("Alice", "Smith"))
        repository.save(Customer("Bob", "Smith"))

        // fetch all customers
        log.info("Customers found with findAll():")
        log.info("-------------------------------")
        for (customer in repository.findAll()) {
            log.info("" + customer)
        }
        log.info("")

        // fetch an individual customer
        log.info("Customer found with findByFirstName('Alice'):")
        log.info("--------------------------------")
        log.info("" + repository.findByFirstName("Alice"))

        log.info("Customers found with findByLastName('Smith'):")
        log.info("--------------------------------")
        for (customer in repository.findByLastName("Smith")) {
            log.info("" + customer)
        }
    }

}

@Document
data class Customer(
        val firstName: String,
        val lastName: String,
        @Id val id: String? = null
)

interface CustomerRepository : MongoRepository<Customer, String> {
    fun findByFirstName(firstName: String): Customer
    fun findByLastName(lastName: String): List<Customer>
}

