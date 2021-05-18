import React from "react";
import axios from "axios";

const MainPage = () => {
  const getTask = () => {
    axios.get('http://localhost:8080/api/v1/task').then(
      () => console.log("Ok")
    )
  }

  return (
    <div>
      <button onClick={getTask}>GET</button>
      <div className="container">
        <form className="form-signin" method="post" action="/login">
          <h2 className="form-signin-heading">Login</h2>
          <p>
            <label htmlFor="username">Username</label>
            <input type="text" id="username" name="username" className="form-control" placeholder="Username" required/>
          </p>
          <p>
            <label htmlFor="role">Role</label>
            <input type="text" id="role" name="role" className="form-control" placeholder="Role" required/>
          </p>
          <p>
            <label htmlFor="password">Password</label>
            <input type="password" id="password" name="password" className="form-control" placeholder="Password"
                   required/>
          </p>
          <button className="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
      </div>
    </div>
  )
}

export default MainPage