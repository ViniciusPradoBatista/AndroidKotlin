package com.example.d_dmaster

data class Personagem(
    var id: Int? = null,
    var nome: String = "",
    var classe: Classe? = null,
    var raca: Raca? = null,
    var forca: Int = 8,
    var destreza: Int = 8,
    var constituicao: Int = 8,
    var inteligencia: Int = 8,
    var sabedoria: Int = 8,
    var carisma: Int = 8,
    var pontosDeVida: Int = 0
) {
    fun aplicarBonusClasse() {
        when (classe?.nome) {
            "Arqueiro" -> destreza += 3
            "Mago" -> inteligencia += 3
            "Guerreiro" -> forca += 3
        }
    }

    fun aplicarBonusRaca() {
        raca?.aplicarBonus(this)
    }

    fun calcularPontosDeVida() {
        pontosDeVida = 10 + constituicao
    }
}
