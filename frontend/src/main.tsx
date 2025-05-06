import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { Router, RouterProvider, createBrowserRouter } from 'react-router-dom'
import './style/index.css'
import App from './App.tsx'
import MoviePage from './MoviePage.tsx'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />
  },
  {
    path: "/movie/:id",
    element: <MoviePage />
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
