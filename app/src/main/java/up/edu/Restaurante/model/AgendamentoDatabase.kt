package up.edu.Restaurante.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


@Database(entities = [AgendamentoNovo::class], version = 1)
abstract class AgendamentoDatabase : RoomDatabase() {

    abstract fun agendamentoDao(): AgendamentoDao

    companion object {
        @Volatile
        private var INSTANCE: AgendamentoDatabase? = null

        fun getInstance(context: Context): AgendamentoDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AgendamentoDatabase::class.java,
                        "agendamento_database"
                    ).build()
                }
                return INSTANCE as AgendamentoDatabase
            }
        }
    }
}