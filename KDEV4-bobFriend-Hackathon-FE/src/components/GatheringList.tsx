import { useState, useEffect, ChangeEvent } from 'react'
import Api from './Api' // Api 모듈은 적절히 수정되어야 합니다.
import { Gathering } from './interfaces/types'
import { User } from './interfaces/types'

export default function GatheringList({ user }: { user: User | null }) {
  const [gatherings, setGatherings] = useState<Gathering[]>([]) // Gathering 타입의 배열
  const [participants, setParticipants] = useState<User[]>([])
  const [loading, setLoading] = useState<boolean>(true) // 로딩 상태
  // const [currentPage, setCurrentPage] = useState<number>(1) // 현재 페이지
  // const [totalPages, setTotalPages] = useState<number>(1) // 전체 페이지 수

  const [showTooltip, setShowTooltip] = useState<boolean>(false)
  const [searchWord, setSearchWord] = useState('')
  const [searchField, setSearchField] = useState('')

  const handleMouseEnter = () => {
    setShowTooltip(true)
  }

  const handleMouseLeave = () => {
    setShowTooltip(false)
  }

  // 모임 게시물 전체 불러오기
  const fetchGatherings = async () => {
    try {
      const response = await Api.get(
        `${process.env.REACT_APP_API_URL}/bobfriend/all`,
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      console.log(response.data)
      setGatherings(response.data)
      gatherings.map(gathering => {
        setParticipants(gathering.participant_list)
      })
      console.log(participants)
      // setTotalPages(response.data.pagination.total_page) // 페이지네이션 로직 추가 필요
      setLoading(false)
    } catch (error) {
      console.error('Gathering 데이터 불러오기 실패:', error)
      setLoading(false)
    }
  }

  // 참석하기
  const handleJoin = async (id: number) => {
    console.log(localStorage.getItem('userId'))
    try {
      const response = await Api.post(
        `${process.env.REACT_APP_API_URL}/bobfriend/gatherings` +
          id +
          '/attend',
        { id: Number(localStorage.getItem('userId')) },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )

      setGatherings(prevGatherings =>
        prevGatherings.map(gathering =>
          gathering.id === id
            ? {
                ...gathering,
                current_participant: gathering.current_participant + 1,
                participant_list: [...gathering.participant_list]
              }
            : gathering
        )
      )
      console.log(response)
      console.log(user)
      console.log(gatherings)
      alert('참석 성공!')
      window.location.reload()
    } catch (error) {
      alert('참석 실패...')
      console.error('Gathering 데이터 불러오기 실패:', error)
    }
  }

  const handleSearch = async (e: ChangeEvent<HTMLInputElement>) => {
    setSearchWord(e.target.value)
    const field = searchField

    const response = await Api.get(
      `${process.env.REACT_APP_API_URL}/bobfriend/gatherings/search?field=` +
        field +
        '&word=' +
        searchWord,
      { headers: { 'Content-Type': 'application/json' } }
    )
    console.log(response.data)
  }

  const handleSearchFieldChange = async (field: string) => {
    setSearchField(field)
  }

  // const handleMap = (latitude: number, longitude: number) => {
  //   if (!window.kakao || !window.kakao.maps) {
  //     console.error('카카오 맵을 불러올 수 없습니다.')
  //     return
  //   }

  //   const container = document.getElementById('map') // 지도 영역
  //   const options = {
  //     center: new window.kakao.maps.LatLng(latitude, longitude), // 지정된 좌표
  //     level: 3 // 확대/축소 레벨
  //   }

  //   new window.kakao.maps.Map(container, options) // 지도 생성
  // }

  useEffect(() => {
    fetchGatherings()
  }, [])

  if (loading) {
    return <p className="text-center text-xl text-gray-600">⏳ 로딩 중...</p>
  }

  return (
    <div className="relative mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
      <div className="relative mx-auto max-w-4xl rounded-lg bg-white p-6 shadow-lg">
        <div className="mb-6 flex items-center space-x-4">
          {/* 버튼들 */}
          <div className="flex space-x-4">
            <button
              className="rounded-full bg-black px-4 py-2 text-white"
              onClick={() => handleSearchFieldChange('I')}>
              내향적
            </button>
            <button
              className="rounded-full bg-black px-4 py-2 text-white"
              onClick={() => handleSearchFieldChange('E')}>
              외향적
            </button>
            <button
              className="rounded-full bg-black px-4 py-2 text-white"
              onClick={() => handleSearchFieldChange('S')}>
              소식좌
            </button>
            <button
              className="rounded-full bg-black px-4 py-2 text-white"
              onClick={() => handleSearchFieldChange('B')}>
              푸드파이터
            </button>
          </div>

          {/* 검색창 */}
          <div className="ml-4 w-full">
            <input
              type="text"
              placeholder="검색..."
              className="w-full rounded-md border border-gray-300 p-3 shadow-sm focus:ring-2 focus:ring-black focus:outline-none"
              value={searchWord}
              onChange={handleSearch}
            />
          </div>
        </div>
      </div>

      <h2 className="mb-4 text-3xl font-bold text-gray-700">
        밥친구 만나기 🥳
      </h2>
      <ul className="space-y-4">
        {gatherings.map(gathering => (
          <li
            key={gathering.id}
            className="rounded-lg bg-gray-50 p-4 shadow-md transition-all hover:shadow-xl">
            <div className="mb-2 flex items-center justify-between">
              <strong className="text-xl font-semibold text-gray-900">
                <div className="mr-3 inline-block w-7 rounded-md bg-black text-center text-white">
                  {gathering.talk_flag}
                </div>
                {gathering.title}
                <span className="text-sm font-semibold text-gray-500">
                  {gathering.talk_thema}
                </span>
              </strong>
              <span className="text-sm text-gray-500"></span>
            </div>
            {/* <p className="text-gray-700">{gathering.content}</p> */}
            <div className="relative mt-3">
              <div className="text-gray-500">
                장소:
                <span
                  className="group relative cursor-pointer text-black"
                  onMouseEnter={handleMouseEnter}
                  onMouseLeave={handleMouseLeave}>
                  {gathering.place.name}

                  {/* Tooltip Box */}
                  <div
                    className="absolute left-1/2 mt-2 w-48 -translate-x-1/2 transform rounded-md bg-black p-2 text-sm text-white opacity-0 transition-opacity duration-300 group-hover:opacity-100"
                    style={{ display: showTooltip ? 'block' : 'none' }}>
                    <p>
                      <strong>장소:</strong> {gathering.place.name}
                    </p>
                    <p>
                      <strong>설명:</strong>{' '}
                      {gathering.place.content || '설명이 없습니다'}
                    </p>
                    <p>
                      <strong>주소:</strong> {gathering.place.address}
                    </p>
                    {/* <div
                      id="map"
                      style={{ width: '500px', height: '400px' }}>
                      onMouseEnter={() => handleMap(33.450701, 126.570667)}
                    </div> */}
                  </div>
                </span>
              </div>
              <p className="text-gray-500">
                모임 일시: {gathering.gathering_at}
              </p>
              <p className="text-gray-500">
                모임 종료 일시: {gathering.closing_at}
              </p>
              <p className="text-gray-500">
                참가 인원: {gathering.current_participant} /{' '}
                {gathering.max_participant}{' '}
                {gathering.participant_list.map(user => (
                  <span key={user.userId}>{user.name}, </span>
                ))}
                <span>{gathering.user_name}(주최자)</span>
              </p>
              <button
                className="absolute right-4 bottom-4 rounded-full bg-black px-4 py-2 text-white shadow-lg transition transition-all duration-100 hover:scale-105"
                onClick={() => handleJoin(gathering.id)}>
                모임 참석
              </button>
              <p className="text-gray-500">
                상태: {gathering.state === 'ING' ? '모집중' : '마감!'}
              </p>
            </div>
          </li>
        ))}
      </ul>
      {/* 페이지네이션 기능 추가 필요 */}
      {/* <Pagination
    currentPage={currentPage}
    totalPages={totalPages}
    onPageChange={setCurrentPage}
    /> */}
    </div>
  )
}
