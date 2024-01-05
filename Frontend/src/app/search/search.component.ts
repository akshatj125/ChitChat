import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  searchValue: any;

  @Output()
  searchValueChanged: EventEmitter<string> = new EventEmitter<string>();

  onSearchValueChanged(){
    this.searchValueChanged.emit(this.searchValue);
  }
}
