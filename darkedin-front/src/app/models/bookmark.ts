import { Tag, MOCK_TAGS } from './tag';

export interface Bookmark {
  id: number;
  title: string;
  url: string;
  registDate: string;
  isFavorite: boolean;
  tags: Tag[];
  comment: string;
}

export const MOCK_BOOKMARK: Bookmark = {id: 1, title: "プログラマによるプログラミングのためのBGMなど、仕事や勉強の邪魔にならない無料で使えそうなBGM集。2021年版 － Publickey", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"}

export var MOCK_BOOKMARKS: Bookmark[] = [
  {id: 1, title: "プログラマによるプログラミングのためのBGMなど、仕事や勉強の邪魔にならない無料で使えそうなBGM集。2021年版 － Publickey", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"},
  {id: 2, title: "低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"},
  {id: 3, title: "低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"},
  {id: 4, title: "低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"},
  {id: 5, title: "低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"},
  {id: 6, title: "低糖質で高タンパクの筋肉めし「炒り豆腐スタ丼」をうま味凝縮で作るレシピ【筋肉料理人】", url: "https://logmi.jp/tech/articles/324298", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "正常に点検が行われていても被曝の危険がある作業だったろうにバッジも支給されていなかったのだろうか。会社が殆ど情報を出さないことも含めどんな労働環境だったのか。スクープがなければ葬られていたのだろうか。"}
]
