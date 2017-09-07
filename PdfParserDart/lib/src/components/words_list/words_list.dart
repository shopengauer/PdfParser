import 'package:PdfParserDart/src/domain/word.dart';
import 'package:angular/angular.dart';

@Component(
  selector: 'words-list',
  templateUrl: 'word_list.html',
  directives: const [CORE_DIRECTIVES]

)
class WordsList{

  @Input()
  List<Word> wordsList;// = [new Word.createWord("qwerty", Language.ENGLISH)];

}