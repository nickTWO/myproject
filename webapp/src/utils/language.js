import { getLocalStorage} from "./helper";

export function languageFunc(chinese,english) {
  return  getLocalStorage('language') === 'en' ? english : chinese
}