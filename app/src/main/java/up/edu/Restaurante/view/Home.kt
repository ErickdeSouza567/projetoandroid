package up.edu.Restaurante.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import up.edu.barbershop.R
import up.edu.Restaurante.adapter.ServicosAdapter
import up.edu.barbershop.databinding.ActivityHomeBinding
import up.edu.Restaurante.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo(a), $nome"
        val recyclerViewServicos = binding.recyclerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this, 2)
        servicosAdapter = ServicosAdapter(this, listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()

        binding.btAgendar.setOnClickListener{
            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
        }
    }

    private fun getServicos(){

        val servico1 = Servicos(R.drawable.img1, "Marmitas")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.img2, "Bebibdas")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.img3, "Compras")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.img4, "Chat")
        listaServicos.add(servico4)
    }
}