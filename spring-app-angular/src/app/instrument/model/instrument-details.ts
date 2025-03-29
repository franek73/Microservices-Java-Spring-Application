import { Category } from "../../category/model/category";

export interface InstrumentDetails {
  uuid: string

  name: string

  description: string

  category: Category
}
