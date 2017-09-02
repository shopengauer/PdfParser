// Copyright (c) 2017, vasilij. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular/angular.dart';
import 'package:http/browser_client.dart';
import 'package:http/http.dart';

import 'package:PdfParserDart/app_component.dart';

void main() {
  bootstrap(AppComponent,[provide(Client,useFactory: () => new BrowserClient(),deps: [])]);
}
