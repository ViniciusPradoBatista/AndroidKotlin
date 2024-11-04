package com.example.d_dmaster

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PersonagemDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "personagem.db"
        private const val DATABASE_VERSION = 2
        const val TABLE_PERSONAGEM = "personagem"
        const val COLUMN_ID = "id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_CLASSE = "classe"
        const val COLUMN_RACA = "raca"
        const val COLUMN_FORCA = "forca"
        const val COLUMN_DESTREZA = "destreza"
        const val COLUMN_CONSTITUICAO = "constituicao"
        const val COLUMN_INTELIGENCIA = "inteligencia"
        const val COLUMN_SABEDORIA = "sabedoria"
        const val COLUMN_CARISMA = "carisma"
        const val COLUMN_PONTOS_DE_VIDA = "pontosDeVida"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PERSONAGEM (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOME TEXT,
                $COLUMN_CLASSE TEXT,
                $COLUMN_RACA TEXT,
                $COLUMN_FORCA INTEGER,
                $COLUMN_DESTREZA INTEGER,
                $COLUMN_CONSTITUICAO INTEGER,
                $COLUMN_INTELIGENCIA INTEGER,
                $COLUMN_SABEDORIA INTEGER,
                $COLUMN_CARISMA INTEGER,
                $COLUMN_PONTOS_DE_VIDA INTEGER
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE $TABLE_PERSONAGEM ADD COLUMN $COLUMN_PONTOS_DE_VIDA INTEGER DEFAULT 0")
        }
    }

    fun addPersonagem(personagem: Personagem): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, personagem.nome)
            put(COLUMN_CLASSE, personagem.classe?.nome)
            put(COLUMN_RACA, personagem.raca?.nome)
            put(COLUMN_FORCA, personagem.forca)
            put(COLUMN_DESTREZA, personagem.destreza)
            put(COLUMN_CONSTITUICAO, personagem.constituicao)
            put(COLUMN_INTELIGENCIA, personagem.inteligencia)
            put(COLUMN_SABEDORIA, personagem.sabedoria)
            put(COLUMN_CARISMA, personagem.carisma)
            put(COLUMN_PONTOS_DE_VIDA, personagem.pontosDeVida)
        }
        val result = db.insert(TABLE_PERSONAGEM, null, values)
        db.close()
        return result
    }

    fun getAllPersonagens(): List<Personagem> {
        val personagens = mutableListOf<Personagem>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_PERSONAGEM, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val personagem = Personagem(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                    classe = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASSE))?.let { Classe(it, mapOf()) },
                    raca = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACA))?.let { Raca(it, bonusAtributos = mapOf()) },
                    forca = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORCA)),
                    destreza = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DESTREZA)),
                    constituicao = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSTITUICAO)),
                    inteligencia = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELIGENCIA)),
                    sabedoria = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SABEDORIA)),
                    carisma = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARISMA)),
                    pontosDeVida = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PONTOS_DE_VIDA))
                )
                personagens.add(personagem)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return personagens
    }

    fun updatePersonagem(id: Int, personagem: Personagem): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, personagem.nome)
            put(COLUMN_CLASSE, personagem.classe?.nome)
            put(COLUMN_RACA, personagem.raca?.nome)
            put(COLUMN_FORCA, personagem.forca)
            put(COLUMN_DESTREZA, personagem.destreza)
            put(COLUMN_CONSTITUICAO, personagem.constituicao)
            put(COLUMN_INTELIGENCIA, personagem.inteligencia)
            put(COLUMN_SABEDORIA, personagem.sabedoria)
            put(COLUMN_CARISMA, personagem.carisma)
            put(COLUMN_PONTOS_DE_VIDA, personagem.pontosDeVida)
        }
        val result = db.update(TABLE_PERSONAGEM, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }

    fun deletePersonagemByName(nome: String): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_PERSONAGEM, "$COLUMN_NOME = ?", arrayOf(nome))
        db.close()
        return result
    }
}