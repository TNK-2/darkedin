import { Tag } from './tag';

export interface Bookmark {
  id: number;
  title: string;
  url: string;
  registDate: Date;
  isFavorite: boolean;
  tags: Tag[];
  comment: string;
}

export const MOCK_BOOKMARKS: Bookmark[] = [
  {id: 1, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"},
  {id: 2, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"},
  {id: 3, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"},
  {id: 4, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"},
  {id: 5, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"},
  {id: 6, title: "test1", url: "https://logmi.jp/tech/articles/324298", registDate: new Date(), isFavorite: true, tags: [{id: 1, name: "taro"}], comment: "test"}
]
