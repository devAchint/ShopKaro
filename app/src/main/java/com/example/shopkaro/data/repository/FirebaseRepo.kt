package com.example.shopkaro.data.repository

import android.util.Log
import com.example.shopkaro.data.models.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepo @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {
    fun user() = firebaseAuth.currentUser

    suspend fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    suspend fun fetchCart(itemId: Int): Int {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in")
        val cartRef = firebaseDatabase.getReference("cart/$userId")
        val query = cartRef.orderByChild("itemId").equalTo(itemId.toDouble()).get().await()
        return if (query.exists()) {
            val cartItem = query.children.first().getValue(CartItem::class.java)
            if (cartItem != null) {
                Log.d("MYDEBUG", cartItem.toString())
                cartItem.quantity
            } else {
                0
            }
        } else {
            0
        }
    }

    suspend fun fetchCartItems(): Flow<List<CartItem>> = flow {
        val userId = firebaseAuth.currentUser?.uid ?: return@flow
        val cartRef = firebaseDatabase.getReference("cart/$userId")
        val snapshot = cartRef.get().await()
        val cartItems = snapshot.children.mapNotNull { it.getValue(CartItem::class.java) }
        emit(cartItems)
    }


    suspend fun addToCart(productId: Int) {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in")
        val cartRef = firebaseDatabase.getReference("cart/$userId")

        val query = cartRef.orderByChild("itemId").equalTo(productId.toDouble()).get().await()

        if (query.exists()) {
            // If the item exists, update its quantity
            val cartItem = query.children.firstOrNull()?.getValue(CartItem::class.java)
            if (cartItem != null) {
                val updatedCartItem = cartItem.copy(quantity = cartItem.quantity + 1)
                query.children.first().ref.setValue(updatedCartItem).await()
            }
        } else {
            // If the item doesn't exist, add it as a new item
            val newCartItemRef = cartRef.push()
            newCartItemRef.setValue(CartItem(productId, 1)).await()
        }
    }


    suspend fun removeFromCart(productId: Int) {
        val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User not logged in")
        val cartRef = firebaseDatabase.getReference("cart/$userId")

        val query = cartRef.orderByChild("itemId").equalTo(productId.toDouble()).get().await()

        if (query.exists()) {
            // If the item exists, update its quantity
            val cartItem = query.children.firstOrNull()?.getValue(CartItem::class.java)
            if (cartItem != null) {
                if (cartItem.quantity > 1) {
                    val updatedCartItem = cartItem.copy(quantity = cartItem.quantity - 1)
                    query.children.first().ref.setValue(updatedCartItem).await()
                } else {
                    query.children.first().ref.removeValue().await()
                }
            }
        }
    }
}