import { ChangeEvent, FormEvent } from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

import axios from 'axios'

interface SignUpFormData {
  name: string
  email: string
  password: string
}

export default function SignUp() {
  const [formData, setFormData] = useState<SignUpFormData>({
    name: '',
    email: '',
    password: ''
    // profile: null
  })
  // const [profileImage, setProfileImage] = useState<string | null>(null)

  const navigate = useNavigate()

  // // ProfileChange
  // const handleProfileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   const file = e.target.files?.[0]
  //   if (file) {
  //     const imageURL = URL.createObjectURL(file)
  //     setProfileImage(imageURL)
  //   }

  //   const value = e.target.files ? e.target.files[0] : null

  //   setFormData(prev => ({
  //     ...prev,
  //     profile: value
  //   }))
  // }

  // const handleUploadClick = () => {
  //   document.getElementById('profileInput')?.click() // 'input' 클릭을 트리거
  // }

  // InputChange
  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const id = e.target.id
    const value = e.target.value

    const prev = formData

    const updatedFormData = {
      ...prev,
      [id]: value
    }
    setFormData(updatedFormData)
  }

  // Submit
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    const form = new FormData()

    form.append('name', formData.name)
    form.append('email', formData.email)
    form.append('password', formData.password)
    // if (formData.email) form.append('profile', formData.email)

    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/bobfriend/signup`,
        form,
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      // 응답 처리
      console.log('회원가입 성공:', response.data)
      navigate('/bobfriend/auth')
    } catch (error) {
      console.error('회원가입 실패:', error)
    }
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-100">
      <div className="w-96 rounded-lg bg-white p-8 shadow-lg">
        <h2 className="mb-6 text-center text-3xl font-bold text-gray-700">
          회원가입
        </h2>

        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label
              htmlFor="name"
              className="block text-sm font-medium text-gray-700">
              이름
            </label>
            <input
              type="text"
              id="name"
              placeholder="이름을 입력하세요"
              className="mt-1 w-full rounded-md border border-gray-300 px-4 py-2 focus:border-indigo-500 focus:ring-indigo-500"
              value={formData.name}
              onChange={handleInputChange}
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="email"
              className="block text-sm font-medium text-gray-700">
              이메일
            </label>
            <input
              type="text"
              id="email"
              placeholder="이메일을 입력하세요"
              className="mt-1 w-full rounded-md border border-gray-300 px-4 py-2 focus:border-indigo-500 focus:ring-indigo-500"
              value={formData.email}
              onChange={handleInputChange}
            />
          </div>

          <div className="mb-4">
            <label
              htmlFor="password"
              className="block text-sm font-medium text-gray-700">
              비밀번호
            </label>
            <input
              type="password"
              className="mt-1 w-full rounded-md border border-gray-300 px-4 py-2 focus:border-indigo-500 focus:ring-indigo-500"
              id="password"
              placeholder="비밀번호를 입력하세요"
              value={formData.password}
              onChange={handleInputChange}
            />
          </div>

          <button
            type="submit"
            className="w-full rounded-md bg-black px-4 py-2 font-semibold text-white">
            가입하기
          </button>
        </form>
      </div>
    </div>
  )
}
