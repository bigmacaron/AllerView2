package kr.kro.fatcats.allerview.model.event

sealed class Request {
    class BarcodeLiked(val barcodeUrl: String) : Request()
    class FoodCodeLiked(val foodCodeUrl: String): Request()
}
