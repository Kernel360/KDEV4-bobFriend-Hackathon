import PlaceList from './components/PlaceList'
import SignUp from './components/SignUp'
import Login from './components/Login'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Header from './components/Header'
import GatheringList from './components/GatheringList'
import { useState } from 'react'
import { User } from './components/interfaces/types'
import CreateGathering from './components/CreateGathering'

export default function App() {
  const [user, setUser] = useState<User>({
    userId: 0,
    name: '',
    email: ''
  })

  return (
    <Router>
      <Routes>
        <Route
          path="/bobfriend"
          element={
            <>
              <Header setUser={setUser} />
              <CreateGathering user={user} />
              <GatheringList user={user} />
            </>
          }
        />
        <Route
          path="/bobfriend/places"
          element={
            <>
              <Header setUser={setUser} />
              <PlaceList user={user} />
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
