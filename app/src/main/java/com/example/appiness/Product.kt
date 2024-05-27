package com.example.appiness

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val availableLanguages: List<String>,
    val sampleReports: Map<String, String>,
    val pages: Int,
    val pagesintext: String,
    val report_type: String,
    val authentic: String,
    val remedies: String,
    val vedic: String,
    val price: Int,
    val discount: Int,
    val appDiscount: Int,
    val couponDiscount: Int,
    val imagePath: ProductImage,
    val soldcount: String,
    val avg: Double
)

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert
    fun insert(product: Product)
}