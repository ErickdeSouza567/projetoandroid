package up.edu.Restaurante.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgendamentoNovo(
    @PrimaryKey val id: Int,
    val horario: String,
    val data: String
)