import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'decodeHtml',
  standalone: true
})
export class DecodeHtmlPipe implements PipeTransform {

  transform(value: string): string {
    const txt = document.createElement("textarea");
    txt.innerHTML = value;
    return txt.value.replace(/&#10;/g, '<br>');
  }

}
