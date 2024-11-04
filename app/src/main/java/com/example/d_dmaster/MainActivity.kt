package com.example.d_dmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.d_dmaster.ui.theme.DDMasterTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {

    private lateinit var dbHelper: PersonagemDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = PersonagemDatabaseHelper(this)
        setContent {
            DDMasterTheme {
                TelaCriacaoPersonagem(dbHelper)
            }
        }
    }
}

@Composable
fun TelaCriacaoPersonagem(dbHelper: PersonagemDatabaseHelper) {
    var nomePersonagem by remember { mutableStateOf("") }
    var personagem by remember { mutableStateOf(Personagem()) }
    var classeSelecionada by remember { mutableStateOf<Classe?>(null) }
    var racaSelecionada by remember { mutableStateOf<Raca?>(null) }
    var pontosRestantes by remember { mutableStateOf(27) }
    var mensagem by remember { mutableStateOf("") }
    var listaPersonagens by remember { mutableStateOf<List<Personagem>>(emptyList()) }
    var personagemParaAtualizar by remember { mutableStateOf<Personagem?>(null) }
    var mostrarPesquisa by remember { mutableStateOf(false) }
    var mostrarDeletar by remember { mutableStateOf(false) }
    var inicializarAtributos by remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    val classes = listOf(
        Classe("Arqueiro", mapOf("Destreza" to 3)),
        Classe("Mago", mapOf("Inteligência" to 3)),
        Classe("Guerreiro", mapOf("Força" to 3))
    )

    val racas = listOf(
        Raca("Humano", bonusAtributos = mapOf("Forca" to 1, "Destreza" to 1, "Constituicao" to 1, "Inteligencia" to 1, "Sabedoria" to 1, "Carisma" to 1)),
        Raca("Elfo", bonusAtributos = mapOf("Destreza" to 2, "Inteligencia" to 1)),
        Raca("Anão", bonusAtributos = mapOf("Constituicao" to 2, "Forca" to 2))
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "Criação de Personagem", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nomePersonagem,
            onValueChange = { nomePersonagem = it },
            label = { Text(text = "Nome do Personagem") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Selecione a Classe:")
        classes.forEach { classe ->
            Row {
                RadioButton(
                    selected = classe == classeSelecionada,
                    onClick = { classeSelecionada = classe }
                )
                Text(text = classe.nome)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Selecione a Raça:")
        racas.forEach { raca ->
            Row {
                RadioButton(
                    selected = raca == racaSelecionada,
                    onClick = { racaSelecionada = raca }
                )
                Text(text = raca.nome)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Pontos restantes: $pontosRestantes")


        AtributoField("Força", personagem.forca, pontosRestantes, onIncrement = {
            val novoValor = personagem.forca + 1
            val custoAtual = calcularCustoPontos(personagem.forca)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.forca = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.forca - 1
            val custoAtual = calcularCustoPontos(personagem.forca)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.forca = novoValor
                pontosRestantes += custoTotal
            }
        })


        AtributoField("Destreza", personagem.destreza, pontosRestantes, onIncrement = {
            val novoValor = personagem.destreza + 1
            val custoAtual = calcularCustoPontos(personagem.destreza)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.destreza = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.destreza - 1
            val custoAtual = calcularCustoPontos(personagem.destreza)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.destreza = novoValor
                pontosRestantes += custoTotal
            }
        })


        AtributoField("Constituição", personagem.constituicao, pontosRestantes, onIncrement = {
            val novoValor = personagem.constituicao + 1
            val custoAtual = calcularCustoPontos(personagem.constituicao)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.constituicao = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.constituicao - 1
            val custoAtual = calcularCustoPontos(personagem.constituicao)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.constituicao = novoValor
                pontosRestantes += custoTotal
            }
        })


        AtributoField("Inteligência", personagem.inteligencia, pontosRestantes, onIncrement = {
            val novoValor = personagem.inteligencia + 1
            val custoAtual = calcularCustoPontos(personagem.inteligencia)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.inteligencia = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.inteligencia - 1
            val custoAtual = calcularCustoPontos(personagem.inteligencia)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.inteligencia = novoValor
                pontosRestantes += custoTotal
            }
        })


        AtributoField("Sabedoria", personagem.sabedoria, pontosRestantes, onIncrement = {
            val novoValor = personagem.sabedoria + 1
            val custoAtual = calcularCustoPontos(personagem.sabedoria)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.sabedoria = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.sabedoria - 1
            val custoAtual = calcularCustoPontos(personagem.sabedoria)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.sabedoria = novoValor
                pontosRestantes += custoTotal
            }
        })


        AtributoField("Carisma", personagem.carisma, pontosRestantes, onIncrement = {
            val novoValor = personagem.carisma + 1
            val custoAtual = calcularCustoPontos(personagem.carisma)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoNovo - custoAtual
            if (pontosRestantes >= custoTotal && novoValor <= 15) {
                personagem.carisma = novoValor
                pontosRestantes -= custoTotal
            }
        }, onDecrement = {
            val novoValor = personagem.carisma - 1
            val custoAtual = calcularCustoPontos(personagem.carisma)
            val custoNovo = calcularCustoPontos(novoValor)
            val custoTotal = custoAtual - custoNovo
            if (novoValor >= 8) {
                personagem.carisma = novoValor
                pontosRestantes += custoTotal
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            personagem.nome = nomePersonagem
            personagem.classe = classeSelecionada
            personagem.raca = racaSelecionada

            personagem.aplicarBonusClasse()
            personagem.aplicarBonusRaca()
            personagem.calcularPontosDeVida()

            dbHelper.addPersonagem(personagem)
            mensagem = "Personagem criado e salvo no banco de dados!"


            nomePersonagem = ""
            classeSelecionada = null
            racaSelecionada = null
            pontosRestantes = 27
            personagem = Personagem(
                forca = 8,
                destreza = 8,
                constituicao = 8,
                inteligencia = 8,
                sabedoria = 8,
                carisma = 8,
                pontosDeVida = personagem.pontosDeVida
            )
        }) {
            Text(text = "Criar Personagem")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            listaPersonagens = dbHelper.getAllPersonagens()
            mostrarPesquisa = true
            mostrarDeletar = false
        }) {
            Text(text = "Pesquisar Personagem")
        }

        if (mostrarPesquisa) {
            listaPersonagens.forEach { personagemItem ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Nome: ${personagemItem.nome}")
                    Text(text = "Classe: ${personagemItem.classe?.nome ?: "Sem Classe"}")
                    Text(text = "Raça: ${personagemItem.raca?.nome ?: "Sem Raça"}")
                    Text(text = "Força: ${personagemItem.forca}")
                    Text(text = "Destreza: ${personagemItem.destreza}")
                    Text(text = "Constituição: ${personagemItem.constituicao}")
                    Text(text = "Inteligência: ${personagemItem.inteligencia}")
                    Text(text = "Sabedoria: ${personagemItem.sabedoria}")
                    Text(text = "Carisma: ${personagemItem.carisma}")
                    Text(text = "Pontos de Vida: ${personagemItem.pontosDeVida}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        personagemParaAtualizar = personagemItem
                        mostrarPesquisa = false
                    }) {
                        Text(text = "Selecionar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        personagemParaAtualizar?.let { personagem ->
            Text(text = "Atualizar Personagem", style = MaterialTheme.typography.headlineSmall)


            if (inicializarAtributos) {
                personagemParaAtualizar = personagemParaAtualizar?.copy(
                    forca = 8,
                    destreza = 8,
                    constituicao = 8,
                    inteligencia = 8,
                    sabedoria = 8,
                    carisma = 8
                )
                pontosRestantes = 27
                inicializarAtributos = false
            }


            TextField(
                value = personagem.nome,
                onValueChange = { novoNome -> personagemParaAtualizar = personagemParaAtualizar?.copy(nome = novoNome) },
                label = { Text("Nome") }
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Selecione a Classe:")
            classes.forEach { classe ->
                Row {
                    RadioButton(
                        selected = personagem.classe == classe,
                        onClick = {
                            personagemParaAtualizar = personagemParaAtualizar?.copy(classe = classe)
                            personagemParaAtualizar?.aplicarBonusClasse()
                        }
                    )
                    Text(text = classe.nome)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(text = "Selecione a Raça:")
            racas.forEach { raca ->
                Row {
                    RadioButton(
                        selected = personagem.raca == raca,
                        onClick = {
                            personagemParaAtualizar = personagemParaAtualizar?.copy(raca = raca)
                            personagemParaAtualizar?.aplicarBonusRaca()
                        }
                    )
                    Text(text = raca.nome)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            AtributoField("Força", personagem.forca, pontosRestantes, onIncrement = {
                val novoValor = personagem.forca + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(forca = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.forca - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(forca = novoValor)
                    pontosRestantes += 1
                }
            })

            AtributoField("Destreza", personagem.destreza, pontosRestantes, onIncrement = {
                val novoValor = personagem.destreza + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(destreza = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.destreza - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(destreza = novoValor)
                    pontosRestantes += 1
                }
            })

            // Repita o bloco `AtributoField` para os outros atributos (Constituição, Inteligência, Sabedoria, Carisma)

            AtributoField("Constituição", personagem.constituicao, pontosRestantes, onIncrement = {
                val novoValor = personagem.constituicao + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(constituicao = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.constituicao - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(constituicao = novoValor)
                    pontosRestantes += 1
                }
            })

            AtributoField("Inteligência", personagem.inteligencia, pontosRestantes, onIncrement = {
                val novoValor = personagem.inteligencia + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(inteligencia = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.inteligencia - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(inteligencia = novoValor)
                    pontosRestantes += 1
                }
            })

            AtributoField("Sabedoria", personagem.sabedoria, pontosRestantes, onIncrement = {
                val novoValor = personagem.sabedoria + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(sabedoria = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.sabedoria - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(sabedoria = novoValor)
                    pontosRestantes += 1
                }
            })

            AtributoField("Carisma", personagem.carisma, pontosRestantes, onIncrement = {
                val novoValor = personagem.carisma + 1
                if (pontosRestantes > 0 && novoValor <= 15) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(carisma = novoValor)
                    pontosRestantes -= 1
                }
            }, onDecrement = {
                val novoValor = personagem.carisma - 1
                if (novoValor >= 8) {
                    personagemParaAtualizar = personagemParaAtualizar?.copy(carisma = novoValor)
                    pontosRestantes += 1
                }
            })

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para salvar as atualizações
            Button(onClick = {
                personagemParaAtualizar?.let {
                    it.calcularPontosDeVida() // Recalcula os pontos de vida antes de salvar
                    it.id?.let { id -> dbHelper.updatePersonagem(id, it) }
                    mensagem = "Personagem atualizado com sucesso!"
                    personagemParaAtualizar = null
                    inicializarAtributos = true // Reativa o reset para futuras atualizações
                }
            }) {
                Text("Salvar Atualização")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            listaPersonagens = dbHelper.getAllPersonagens()
            mostrarPesquisa = false
            mostrarDeletar = true
        }) {
            Text(text = "Deletar Personagem")
        }

        if (mostrarDeletar) {
            listaPersonagens.forEach { personagemItem ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Nome: ${personagemItem.nome}")
                    Text(text = "Classe: ${personagemItem.classe?.nome ?: "Sem Classe"}")
                    Text(text = "Raça: ${personagemItem.raca?.nome ?: "Sem Raça"}")
                    Text(text = "Força: ${personagemItem.forca}")
                    Text(text = "Destreza: ${personagemItem.destreza}")
                    Text(text = "Constituição: ${personagemItem.constituicao}")
                    Text(text = "Inteligência: ${personagemItem.inteligencia}")
                    Text(text = "Sabedoria: ${personagemItem.sabedoria}")
                    Text(text = "Carisma: ${personagemItem.carisma}")
                    Text(text = "Pontos de Vida: ${personagemItem.pontosDeVida}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        dbHelper.deletePersonagemByName(personagemItem.nome)
                        listaPersonagens = listaPersonagens.filter { it.id != personagemItem.id }
                        mensagem = "Personagem deletado com sucesso!"
                    }) {
                        Text(text = "Deletar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mensagem)
    }
}


fun calcularCustoPontos(pontos: Int): Int {
    return when (pontos) {
        8 -> 0
        9 -> 1
        10 -> 2
        11 -> 3
        12 -> 4
        13 -> 5
        14 -> 7
        15 -> 9
        else -> 0 // Para qualquer valor fora do intervalo (deve estar entre 8 e 15)
    }
}

@Composable
fun AtributoField(
    nomeAtributo: String,
    valorAtributo: Int,
    pontosRestantes: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row {
        Text(text = "$nomeAtributo: $valorAtributo")
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onDecrement() }, enabled = valorAtributo > 8) {
            Text(text = "-")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onIncrement() }, enabled = valorAtributo < 15 && pontosRestantes > 0) {
            Text(text = "+")
        }
    }
}
