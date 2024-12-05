package com.faskan

import org.eclipse.microprofile.graphql.DefaultValue
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class HelloGraphQLResource {

    @Query("customerProducts")
    @Description("return all customer products")
    fun sayHello(name: String): CustomerProductHolding {
        return CustomerProductHolding(
            mobileLines = listOf(
                MobileLine.StandardMobileLine("1", "12345678"),
                MobileLine.BundleMobileLine("2", "87654321", "1")
            ),
            internetLine = listOf(
                InternetLine.StandardInternetLine("3", 100),
                InternetLine.BundleInternetLine("4", 200, "1")
            )
        )
    }

}

data class CustomerProductHolding(
    val mobileLines: List<MobileLine>,
    val internetLine: List<InternetLine>
)
sealed interface CustomerProduct {
    val id: String
}

sealed interface MobileLine : CustomerProduct {
    val phoneNumber: String
    data class StandardMobileLine(
        override val id: String,
        override val phoneNumber: String
    ) : MobileLine

    data class BundleMobileLine(
        override val id: String,
        override val phoneNumber: String,
        val bundleId: String
    ) : MobileLine
}

sealed interface InternetLine : CustomerProduct {
    val speed: Int

    data class StandardInternetLine(
        override val id: String,
        override val speed: Int
    ) : InternetLine

    data class BundleInternetLine(
        override val id: String,
        override val speed: Int,
        val bundleId: String
    ) : InternetLine
}