const SET_USER_DATA = 'SET_USER_DATA'
const LOGOUT = 'LOGOUT'


let initialState = {
  userId: null,
  jwtToken: null,
  refreshToken: null,
  email: null,
  isAuth: false
}

const AuthReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_USER_DATA:
      return {
        ...state,
        ...action.data
      }
    case LOGOUT:
      return {
        userId: null,
        jwtToken: null,
        refreshToken: null,
        email: null,
        isAuth: false
      }
    default:
      return state
  }
}

export default AuthReducer