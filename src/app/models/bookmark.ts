import { Tag, MOCK_TAGS } from './tag';

export interface Bookmark {
  id: number;
  title: string;
  url: string;
  summary: string;
  registDate: string;
  isFavorite: boolean;
  tags: Tag[];
  comment: string;
}

export const MOCK_BOOKMARK: Bookmark = {id: 1, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttesttesttesttesttesttesttesttesttestte", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"}

export var MOCK_BOOKMARKS: Bookmark[] = [
  {id: 1, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"},
  {id: 2, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"},
  {id: 3, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"},
  {id: 4, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"},
  {id: 5, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"},
  {id: 6, title: "test1", url: "https://logmi.jp/tech/articles/324298", summary: "testtesttest", registDate: "2021/06/08", isFavorite: true, tags: [MOCK_TAGS[0], MOCK_TAGS[1]], comment: "test"}
]
