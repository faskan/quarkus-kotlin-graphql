package com.faskan

import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class HelloGraphQLResource {

    @Query("customerProducts")
    @Description("return all customer products")
    fun sayHello(name: String): CustomerProducts {
        return CustomerProducts(
            mobileLines = listOf(
                CustomerProduct.MobileLine("1", "12345678")
            ),
            internetLine = listOf(
                CustomerProduct.InternetLine("3", 100)
            )
        )
    }

}

data class CustomerProducts(
    val mobileLines: List<CustomerProduct>,
    val internetLine: List<CustomerProduct>
)
sealed interface CustomerProduct {
    val id: String

    data class MobileLine(override val id: String, val phoneNumber: String) : CustomerProduct
    data class InternetLine(override val id: String, val speed: Int) : CustomerProduct

}

