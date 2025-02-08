package com.suvilao.pixcoin.responses

import java.io.Serializable

data class Machine(
    val codigo: String,
    val operacao: String,
    val urlServidor: String,
    val webhook01: String,
    val webhook02: String,
    val rotaConsultaStatusMaq: String,
    val rotaConsultaAdimplencia: String,
    val idMaquina: String,
    val idCliente: String,
    val valor1: Int,
    val valor2: Int,
    val valor3: Int,
    val valor4: Int,
    val textoEmpresa: String,
    val corPrincipal: String,
    val corSecundaria: String,
    val minValue: Int,
    val maxValue: Int,
    val identificadorMaquininha: String,
    val serialMaquininha: String,
    val macaddressMaquininha: String,
    val operadora: String,
    val createdAt: String
) : Serializable

data class GetLittleMachineResponse(
    val maquina: Machine
)
