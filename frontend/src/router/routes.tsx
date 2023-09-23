import React, { FC } from "react";
import { Route, Routes, Navigate } from "react-router-dom";

interface RouteType {
  path: string;
  element: React.LazyExoticComponent<FC>;
}

const routes: RouteType[] = [
  {
    path: "/",
    element: React.lazy(() => import("@/pages/landing")),
  },
  {
    path: "/flip",
    element: React.lazy(() => import("@/components/cardFlip")),
  },
  {
    path: "/main",
    element: React.lazy(() => import("@/pages/main")),
  },
  {
    path: "/crystal",
    element: React.lazy(() => import("@/pages/crystalBall")),
  },
  {
    path: "/signIn",
    element: React.lazy(() => import("@/pages/login")),
  },
  {
    path: "/profile",
    element: React.lazy(() => import("@/pages/profile")),
  },
  {
    path: "/menu",
    element: React.lazy(() => import("@/pages/menu")),
  },
  {
    path: "/faq",
    element: React.lazy(() => import("@/pages/faq")),
  },
  {
    path: "/luck",
    element: React.lazy(() => import("@/pages/luck")),
  },
  {
    path: "/luckcontent",
    element: React.lazy(() => import("@/pages/luckContent")),
  },
  {
    path: "/landing",
    element: React.lazy(() => import("@/pages/landing")),
  },
  {
    path: "/login/oauth/redirect",
    element: React.lazy(() => import("@/pages/redirect")),
  },
  {
    path: "/dictionary",
    element: React.lazy(() => import("@/pages/dictionary")),
  },
  {
    path: "/spend",
    element: React.lazy(() => import("@/pages/spendPattern")),
  },
  {
    path: "/next",
    element: React.lazy(() => import("@/pages/nextSpendPattern")),
  },
  {
    path: "/card",
    element: React.lazy(() => import("@/pages/cardRecommend")),
  },
  // {
  //   path: "/",
  //   element: () => <Navigate replace to="/main" />,
  // },
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
