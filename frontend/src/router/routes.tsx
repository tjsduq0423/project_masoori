import React, { FC } from "react";
import { Route, Routes, Navigate } from "react-router-dom";

interface RouteType {
  path: string;
  element: React.LazyExoticComponent<FC>;
}

const routes: RouteType[] = [
  {
    path: "/",
    // guard: LoginGuard,
    // layout: DashboardLayout,
    element: React.lazy(() => import("@/components/Pokemon")),
  },
  {
    path: "/deck",
    element: React.lazy(() => import("@/components/Deck")),
  },
  {
    path: "/flip",
    element: React.lazy(() => import("@/components/CardFlip")),
  },
  {
    path: "/main",
    element: React.lazy(() => import("@/pages/main")),
  },
  {
    path: "/card",
    element: React.lazy(() => import("@/pages/cardrecommendation")),
  },
  {
    path: "/book",
    element: React.lazy(() => import("@/pages/bookpage")),
  },
  {
    path: "/crystal",
    element: React.lazy(() => import("@/components/CrystalBall")),
  },
  //   {
  //     path: "/",
  //     element: () => <Navigate replace to="/menu" />,
  //   },
  //   {
  //     path: "/*",
  //     element: () => <Navigate replace to="/404" />,
  //   },
];

const RenderRoutes: FC = () => {
  return (
    <React.Suspense fallback={<div>Loading...</div>}>
      <Routes>
        {routes.map((route, i) => {
          const RouteElement = route.element;
          //   const RouteLayout = route.layout || React.Fragment;
          //   const Guard = route.guard || React.Fragment;

          return <Route key={i} path={route.path} element={<RouteElement />} />;
        })}
      </Routes>
    </React.Suspense>
  );
};

export default RenderRoutes;
