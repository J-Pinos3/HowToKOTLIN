package com.reverb.crud_compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Formulario(
    idUsuario: String,
    nombre:String,
    funNombre: (String)->Unit,
    estaEditando:Boolean,
    funEstaEditando: ()->Unit,
    textButton: String,
    funTextButton:(String)->Unit,
    funResetCampos:()->Unit,
    viewModel: UsuariosViewModel
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = nombre,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true, maxLines = 1,
        onValueChange = { funNombre(it) },
        label = { Text(text = "Nombre") }
    )
    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if(estaEditando){
                viewModel.updateUsuario(
                    idUsuario = idUsuario,
                    usuario= Usuario(  idUsuario = idUsuario, nombre = nombre  )
                )
                funTextButton("Agregar Usuario")
                funEstaEditando()
            }else{
                viewModel.addUsuarios(
                    Usuario(  idUsuario = "", nombre = nombre  )
                )
            }
            funResetCampos()
        }
    ){
        Text(text = textButton)
    }
}