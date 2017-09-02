// Copyright (c) 2017, vasilij. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular/angular.dart';
import 'package:http/http.dart';
import 'dart:async';


// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [],
  providers: const [],
)
class AppComponent {
  // Nothing here yet. All logic is in TodoListComponent.
   String dog;
  Client _http;


  AppComponent(this._http);

  Future<String> getDog() async {
    final response = await _http.get("word/book/all-tokens");
    dog = response.body;
    print(dog);
    return response.body;
  }

}
