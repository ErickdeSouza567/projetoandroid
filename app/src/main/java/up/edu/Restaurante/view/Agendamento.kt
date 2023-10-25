package up.edu.Restaurante.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import up.edu.barbershop.databinding.ActivityAgendamentoBinding
import up.edu.Restaurante.model.AgendamentoDatabase
import up.edu.Restaurante.model.AgendamentoDao
import up.edu.Restaurante.model.AgendamentoNovo
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private lateinit var database: AgendamentoDatabase
    private lateinit var agendamentoDao: AgendamentoDao

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the database instance
        database = AgendamentoDatabase.getInstance(this)
        agendamentoDao = database.agendamentoDao()

        val calendar = Calendar.getInstance()

        binding.datePicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val data = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

            binding.btAgendado.setOnClickListener {
                val horario = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)

                if (horario != null && data != null) {
                    // Gera um id aleatório para o novo agendamento
                    val id = Random().nextInt(1000)

                    val agendamento = AgendamentoNovo(id, horario, data)

                    // Insert agendamento using a coroutine
                    CoroutineScope(Dispatchers.IO).launch {
                        agendamentoDao.inserirAgendamento(agendamento)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@Agendamento, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Selecione a data e hora antes de agendar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
        }
    }
}