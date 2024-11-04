package com.example.d_dmaster

data class Raca(
    val nome: String,
    val subRaca: String? = null,
    val bonusAtributos: Map<String, Int>
) {
    fun aplicarBonus(personagem: Personagem) {
        bonusAtributos.forEach { (atributo, bonus) ->
            when (atributo) {
                "Forca" -> personagem.forca += bonus
                "Destreza" -> personagem.destreza += bonus
                "Constituicao" -> personagem.constituicao += bonus
                "Inteligencia" -> personagem.inteligencia += bonus
                "Sabedoria" -> personagem.sabedoria += bonus
                "Carisma" -> personagem.carisma += bonus
            }
        }
    }
}
