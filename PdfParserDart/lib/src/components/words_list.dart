import 'package:PdfParserDart/src/components/word.dart';
import 'package:angular/angular.dart';

@Component(
  selector: 'words-list',
  template: '''
              <div *ngFor = "let word of wordsList"><p>{{word.token}}</p></div>
            ''',
  directives: const [CORE_DIRECTIVES]

)
class WordsList{

  @Input()
  List<Word> wordsList;// = [new Word.createWord("qwerty", Language.ENGLISH)];

}