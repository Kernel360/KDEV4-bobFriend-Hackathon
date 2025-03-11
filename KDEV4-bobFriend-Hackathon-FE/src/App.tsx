import PostList from './components/PostList'
import SignUp from './components/SignUp'
import Login from './components/Login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UserInfo from './components/UserInfo'

export default function App() {
  return (
    <Router>
      <Routes>
        <Route
          path="/bobfriend"
          element={
            <>
              <UserInfo />
              <PostList />
            </>
          }
        />
        <Route
          path="/bobfriend/signup"
          element={<SignUp />}
        />
        <Route
          path="/bobfriend/auth"
          element={<Login />}
        />
      </Routes>
    </Router>
  )
}
