package com.example.projectuasmobile.table

class Profile (
    var id_perusahaan: String? = null,
    var fotoImagePer: String? = null,
    var namaPerusahaan: String? = null,
    var imageUrlPerusahaan: String?
)
{
    constructor(): this("", "","", "")
}
