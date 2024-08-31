package com.reverb.crud_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.Serial

@Composable
fun ScreenCRUD(listaUsuarios: ArrayList<Usuario>, viewModel: UsuariosViewModel){

    var id by remember {  mutableStateOf("")  }
    var nombre by remember {  mutableStateOf("")  }
    var email by remember {  mutableStateOf("")  }
    var estaEditando by remember {  mutableStateOf(false)  }
    var textButton by remember {  mutableStateOf("Agregar Usuario")  }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Formulario(
                idUsuario = id,
                nombre = nombre,
                funNombre = { nombre = it },
                estaEditando = estaEditando,
                funEstaEditando = { estaEditando = false },
                textButton = textButton,
                funTextButton = { textButton = it },
                funResetCampos = {
                    nombre  = ""
                    email = ""
                },
                viewModel = viewModel
            )
            Column(  modifier = Modifier.fillMaxWidth()  ){
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ){
                    items(listaUsuarios){ usuario->
                        CardUsuario(
                            usuario = usuario,
                            funIdUsuario = {id = it},
                            funNombre = { nombre = it },
                            funTextButton = {textButton = it},
                            funEstaEditando = { estaEditando = it },
                            funBorrarUsuario = {  viewModel.deleteUsuario(usuario.idUsuario)  }
                        )
                    }
                }
            }
        }
    }
}