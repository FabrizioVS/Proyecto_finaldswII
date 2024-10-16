import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  private baseUrl = environment.apiUrl;

  private tipo: string =`${this.baseUrl}/tipo/todos`

  constructor(private http: HttpClient) 
  { }


  listarTipos():Observable<any>{
    return this.http.get(this.tipo)
  }
}
