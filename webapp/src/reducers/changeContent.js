const changeContent =  (state = [], action) =>{
  switch (action.type){
    case 'CHANGE' :
      return action.item;
    default:
      return state
  }
}
export default changeContent