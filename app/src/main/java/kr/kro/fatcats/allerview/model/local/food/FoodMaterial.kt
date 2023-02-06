package kr.kro.fatcats.allerview.model.local.food

data class FoodMaterial(
    val grainProducts : Grain, // 곡물류
    val meatProducts : Meat, //육류
    val fruitProducts : Fruit,//과일류
    val seafoodProducts: SeaFood,//해산물
    val etcProducts: Etc //기타
)
data class Grain(
    val buckwheat : String , // 메밀
    val wheat : String, //밀
    val soybean : String, //대두
    val walnut : String, //호두
    val peanut : String //땅콩
)
data class Meat(
    val egg : String, //달걀(계란)
    val milk : String, //우유
    val chicken : String, //닭고기
    val beef : String , //쇠고기
    val pork : String // 돼지고기
)
data class Fruit(
    val peach : String,//복숭아
    val tomato : String //토마토
)
data class SeaFood(
    val shrimp : String,//새우
    val mackerel : String, //고등어
    val mussel : String , //홍합
    val abalone : String, //전복
    val oyster : String, //굴
    val shellfish : String,//조개류
    val crab : String, //게
    val squid : String //오징어
)
data class Etc(
    val sulfurousAcid : String // 아황산 (소시지등에 들어감)
)

