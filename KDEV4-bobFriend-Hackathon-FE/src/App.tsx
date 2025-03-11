import PlaceList from './components/PlaceList'
import SignUp from './components/SignUp'
import Login from './components/Login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UserInfo from './components/UserInfo'
import GatheringList from './components/GatheringList'

export default function App() {
  return (
    <Router>
      <Routes>
        <Route
          path="/bobfriend/gatherings"
          element={
            <>
              <UserInfo />
              <GatheringList />
            </>
          }
        />
        <Route
          path="/bobfriend/places"
          element={
            <>
              <UserInfo />
              <PlaceList />
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
