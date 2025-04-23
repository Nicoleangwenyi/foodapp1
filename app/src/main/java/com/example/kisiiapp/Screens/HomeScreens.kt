package com.example.kisiiapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kisiiapp.AuthViewModel
import com.example.kisiiapp.R

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel) {

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

        // Location & Notifications
        TopBar()

        // Featured Restaurants Section
        Spacer(modifier = Modifier.height(5.dp))
        Categories()

        // Top Picks Section
        Spacer(modifier = Modifier.height(5.dp))
        TopPicksSection()

        // Restaurants Near You Section
        Spacer(modifier = Modifier.height(5.dp))
        RestaurantsNearYou()

        // Today's Offers Section
        Spacer(modifier = Modifier.height(5.dp))
        TodaysOffers()

        Spacer(modifier = Modifier.height(5.dp))
        AllRestaurants()

        // Navigation Bar (If Necessary)
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Column for "Delivering to" and the location name
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Location Icon
            Icon(
                painter = painterResource(id = R.drawable.pin), // Replace with your location icon
                contentDescription = "Location",
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // Space between the icon and text

            // Column for the "Delivering to" text and the location name
            Column(
                horizontalAlignment = Alignment.Start

            ) {
                // "Delivering to" Text
                Text(
                    text = "Delivering to",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Location Name with Dropdown
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Location Name
                    Text(
                        text = "344 Aspen Street", // Replace with actual location data
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    // Dropdown Icon
                    IconButton(onClick = { /* TODO: Handle dropdown, like connecting to maps */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.down), // Replace with your dropdown icon
                            contentDescription = "Dropdown",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        // Notification Icon on the right side
        IconButton(onClick = { /* Handle notifications */ }) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
fun Categories() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Section Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Categories", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green),
                modifier = Modifier.clickable { /* Navigate to full list */ }
            )
        }

        // Horizontal Scroll for Restaurant Cards
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) { index ->
                CategoriesCard(name = "Category ${index + 1}")
            }
        }
    }
}

@Composable
fun CategoriesCard(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        // Circular Image (ONLY for featured restaurants)
        Image(
            painter = painterResource(id = R.drawable.pizza), // Replace with actual image resource
            contentDescription = "Restaurant Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp) // Adjust size as needed
                .clip(CircleShape)
                .border(1.dp, Color.Black, CircleShape)
        )

        // Restaurant Name
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun TopPicksSection() {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Top picks this week", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green),
                modifier = Modifier.clickable { /* Navigate to full list */ }
            )
        }

        LazyRow {
            items(3) { index ->
                RestaurantCardWithDetails(
                    name = "Restaurant ${index + 1}",
                    rating = 4.9,
                    deliveryFee = "200 ksh",
                    time = "20 mins",
                    imageRes = R.drawable.restaurant1 // Replace with actual image
                )
            }
        }
    }
}

@Composable
fun RestaurantCardWithDetails(name: String, rating: Double, deliveryFee: String, time: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                // Row for restaurant name (left) and rating (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Ensures left and right alignment
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFD78414),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "$rating", fontWeight = FontWeight.Bold)
                    }
                }

                // Row for delivery fee (left) and time (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery fee: $deliveryFee",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = time,
                        fontWeight = FontWeight.Light
                    )
                }
            }


        }
    }
}

@Composable
fun RestaurantsNearYou() {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Restaurants Near You", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green),
                modifier = Modifier.clickable { /* Navigate to full list */ }
            )
        }

        LazyRow {
            items(3) { index ->
                RestaurantsNearYouCard(
                    name = "Restaurant ${index + 1}",
                    rating = 4.9,
                    deliveryFee = "200 ksh",
                    time = "20 mins",
                    imageRes = R.drawable.restaurant1 // Replace with actual image
                )
            }
        }
    }
}

@Composable
fun RestaurantsNearYouCard(name: String, rating: Double, deliveryFee: String, time: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                // Row for restaurant name (left) and rating (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Ensures left and right alignment
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFD78414),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "$rating", fontWeight = FontWeight.Bold)
                    }
                }

                // Row for delivery fee (left) and time (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery fee: $deliveryFee",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = time,
                        fontWeight = FontWeight.Light
                    )
                }
            }


        }
    }
}



@Composable
fun TodaysOffers() {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Today's Offers", style = MaterialTheme.typography.titleLarge)
            Text(text = "See all", color = Color.Green, fontWeight = FontWeight.Bold)
        }
        LazyRow {
            items(3) { index ->
                RestaurantCardWithOffer(name = "Restaurant ${index + 1}",
                    rating = 4.9,
                    deliveryFee = "200 ksh",
                    time = "20 mins"
                    )
            }
        }
    }
}

@Composable
fun RestaurantCardWithOffer(name: String, rating: Double, deliveryFee: String, time: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.restaurant2), // Replace with actual image
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart) // Moves it to the bottom-left
                        .padding(start = 6.dp, bottom = 80.dp)
                        .background(Color(0xFF90D91B), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = "Offers available",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                // Row for restaurant name (left) and rating (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Ensures left and right alignment
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFD78414),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "$rating", fontWeight = FontWeight.Bold)
                    }
                }

                // Row for delivery fee (left) and time (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery fee: $deliveryFee",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = time,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}
@Composable
fun AllRestaurants() {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "All Restaurants", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green),
                modifier = Modifier.clickable { /* Navigate to full list */ }
            )
        }

        LazyRow {
            items(3) { index ->
                AllRestaurantsCard(
                    name = "Restaurant ${index + 1}",
                    rating = 4.9,
                    deliveryFee = "200 ksh",
                    time = "20 mins",
                    imageRes = R.drawable.restaurant1 // Replace with actual image
                )
            }
        }
    }
}

@Composable
fun AllRestaurantsCard(name: String, rating: Double, deliveryFee: String, time: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                // Row for restaurant name (left) and rating (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Ensures left and right alignment
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFD78414),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "$rating", fontWeight = FontWeight.Bold)
                    }
                }

                // Row for delivery fee (left) and time (right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery fee: $deliveryFee",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = time,
                        fontWeight = FontWeight.Light
                    )
                }
            }


        }
    }
}


