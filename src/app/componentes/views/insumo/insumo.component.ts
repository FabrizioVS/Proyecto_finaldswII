import { convertPropertyBinding } from '@angular/compiler/src/compiler_util/expression_converter';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InsumoService } from 'src/app/servives/insumo.service';
import { TipoService } from 'src/app/servives/tipo.service';

import Swal from 'sweetalert2';

declare var bootstrap: any;


@Component({
  selector: 'app-insumo',
  templateUrl: './insumo.component.html',
  styleUrls: ['./insumo.component.css']
})
export class InsumoComponent implements OnInit {


listarTipos: any[] = []
listarInsumos: any[] = []
formInsumo: FormGroup
formTipo:FormGroup
title: any
nameBoton: any
id: number

  constructor(private _insumoService: InsumoService, private _tipoService:TipoService) 
  { }

  ngOnInit(): void {
    this.obtenerTipos()
    this.obtenerInsumos()
    this.initForm()
  }

  initForm(){
    this.formInsumo = new FormGroup
    (
      {
        nombre: new FormControl(null,[Validators.required]),
        estado: new FormControl('A'),
        idtipo: new FormControl(1,[Validators.required])
      }
    )

  }

  obtenerTipos(){
    this._tipoService.listarTipos()
    .subscribe((data)=>{
      this.listarTipos = data.tipo;
      console.log(data.tipo)
      if(this.listarTipos.length ==0){
        console.log("No hay datos")
      }
    });
  }


  obtenerInsumos(){
    this._insumoService.listarInsumos()
    .subscribe((data)=>{
      this.listarInsumos = data.insumos;
      console.log(data.insumos)
      if(this.listarInsumos.length ==0){
        console.log("No hay datos")
      }
    });
  }

  obtenerInusmosPorId(id: any){
    let form = this.formInsumo
    this._insumoService.obtenerInsumo(id)
    .subscribe((data)=>{
      form.controls['nombre'].setValue(data.insumos.nombre)
      form.controls['estado'].setValue(data.insumos.estado)
      form.controls['tipo'].setValue(data.insumo.tipo.id)
      console.log(data.insumos)
    });
  }

eliminarInsumo(id:any)
{
  Swal.fire({
    title: '¿Estas seguro de elmiinar el producto',
    icon: 'error',
    showCancelButton: true,
    confirmButtonText: 'Sí, eliminar',
    cancelButtonText: 'Cancelar'
  }).then((result)=>
  {
    if(result.isConfirmed){

      this._insumoService.eliminarInsumos(id)
      .subscribe((data)=>{
        console.log("Insumo Eliminado",data)
        this.listarInsumos = this.listarInsumos.filter(item => item.id !== id);
      },error =>{
        console.error("Error al eliminar el insumo", error);
        
      });

      this.alertaExitosa("eliminado")
    }
  });
}

registrarInusmo(formulario: any): void {
  if (this.formInsumo.valid) {
    console.log("Formulario enviado:", formulario); 
    this._insumoService.generarInsumos(formulario).subscribe(response => {
      this.cerrarModal();
      this.obtenerInsumos();
      this.resetForm();
      console.log("Insumo registrado", response);
    }, error => {
      console.log("Error al registrar Insumo", error);
    });
  } else {
    console.log("Formulario no es válido.");
  }
}


editarInumo(id:number, formulario: any): void{
  if(this.formInsumo.valid){
    console.log("Formulario enviado:", formulario); 
    this._insumoService.editarInsumo(id,formulario).subscribe(response =>{
      this.cerrarModal()
      this.obtenerInsumos()
      this.resetForm()
      console.log("Insumo modificado", response);
    }, error =>{
      console.log("Error al modificar insumo", error);
    });
  }
}

titulo(titulo: any, id: number) {
  this.title = `${titulo} producto`
  titulo == "Crear" ? this.nameBoton = "Guardar" : this.nameBoton = "Modificar"
  if (id != null) {
    this.id = id
    this.obtenerInusmosPorId(id)
  }
}

crearEditarInsumo(boton: any) {
  if (boton == "Guardar") {
    this.formInsumo.controls['estado'].setValue('A')
    this.alertRegistro()
  } else {
    this.alertModificar()
  }
}


  cerrarModal() {
    const modalElement = document.getElementById('modalInsumo');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  }

  resetForm(): void {
    this.formInsumo.reset();
    this.formInsumo.controls['estado'].setValue('A')
  }

  cerrarBoton() {
    this.resetForm()
    this.cerrarModal()
  }

  alertRegistro() {
    if (this.formInsumo.valid) {
      Swal.fire({
        title: '¿Estás seguro de registrar el insumo?',
        icon: 'success',
        showCancelButton: true,
        confirmButtonText: 'Sí, confirmar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.registrarInusmo(this.formInsumo.value)
          this.alertaExitosa("registrado")
        }
      });
    }
   
  }

  alertModificar() {
    if (this.formInsumo.valid) {
      Swal.fire({
        title: '¿Estás seguro de modificar el insumo?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, modificar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.editarInumo(this.id, this.formInsumo.value)
          this.alertaExitosa("modificado")
        }
      });
    }
  }

  alertaExitosa(titulo: any){
    Swal.fire({
      position: "top-end",
      icon: "success",
      title: "Insummo "+titulo,
      showConfirmButton: false,
      timer: 1500
    });
  }


}
