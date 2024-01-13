package com.example.projectuasmobile.table

class Lowongan (
    var id_lowongan: String? = null,
    var lowImage: String? = null,
    var posisiLow: String? = null,
    var durasiLow: String? = null,
    var statusLow: String? = null,
    var syaratLow: String? = null,
    var penjelasanLow: String? = null,
    var namaInstansi: String? = null,
    var jumlahLow: String? = null,
    var imageUrl: String? = null,
)
{
    constructor(): this("", "","", "","", "", "", "", "", "")
}
