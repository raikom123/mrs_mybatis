# Spring boot + MyBatisによる会議室予約システム

## 内容

Spring Boot + MyBatis + JPA + Thymeleaf + Security で会議室予約のWebアプリを作成しました。  
概要は以下の通りです。

- 会議室の一覧が表示される
- 会議室の予約について
  - 会議室と日付ごとに予約状況を確認できる
  - 会議室、日付、時間帯は重複できない
  - 通知日、通知時刻、参加人数、メモの入力は任意
- アプリはCRUD機能を持つ
- ログ出力を行う
- ログイン機能を持つ。
- DBはdockerで起動したpostgreSQLに接続する。
- 単体テストではH2DBを使用する。

## 注意点

- etc/dockerフォルダで"docker-compose up"することでDBを起動できる。
- 各WebDriverをが置いてあるフォルダにPATHを通してから実行する必要がある。

## 確認方法

Spring Bootアプリケーション起動後、以下のURLで起動できます。

<http://localhost:8080/>