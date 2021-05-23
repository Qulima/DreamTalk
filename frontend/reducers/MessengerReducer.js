let initialState = {
  dialogs: [
    {
      id: 1,
      name: 'dialog1',
      ava: 'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png',
      lastMessage: 'Hi'
    },
    {
      id: 2,
      name: 'dialog2',
      ava: 'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png',
      lastMessage: 'Salut'
    },
    {
      id: 3,
      name: 'dialog3',
      ava: 'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png',
      lastMessage: 'Hello'
    }
  ]
}

const MessengerReducer = (state = initialState, action) => {
  switch (action.type) {
    default:
      return state
  }
}

export default MessengerReducer