package up.edu.Restaurante.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AgendamentoDao {

    @Insert
    fun inserirAgendamento(agendamento: AgendamentoNovo)

    @Query("SELECT * FROM agendamentonovo")
    fun listarAgendamentos(): List<AgendamentoNovo>
}
