package com.example.d_dmaster

import java.util.logging.Logger
import java.util.logging.Level
import java.util.logging.ConsoleHandler
import java.util.logging.SimpleFormatter

class DistribuicaoPersonalizada : DistribuicaoAtributosStrategy {
    private val logger: Logger = Logger.getLogger(DistribuicaoPersonalizada::class.java.name)
    private var pontosRestantes = 27

    init {
        val consoleHandler = ConsoleHandler()
        consoleHandler.level = Level.ALL
        consoleHandler.formatter = SimpleFormatter()
        logger.addHandler(consoleHandler)
        logger.useParentHandlers = false
    }

    override fun distribuir(personagem: Personagem, pontos: Int) {

        logger.info("Distribuindo $pontos pontos para os atributos do personagem.")
    }
}
