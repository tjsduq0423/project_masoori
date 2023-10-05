import React, { FC, useEffect, useState } from "react";
import { EventSourcePolyfill, NativeEventSource } from "event-source-polyfill";
import { Route, Routes, Navigate } from "react-router-dom";
import DashboardLayout from "@/layouts/DashboardLayout";
import LoadingPage from "@/pages/loading";
import { error } from "console";

type DashboardLayout = any;

interface RouteType {
  path: string;
  layout?: FC<DashboardLayout>;
  element: React.LazyExoticComponent<FC>;
}

const routes: RouteType[] = [
  {
    path: "/",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/main")),
  },
  {
    path: "/main",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/main")),
  },
  {
    path: "/crystal",
    element: React.lazy(() => import("@/pages/crystalBall")),
  },
  {
    path: "/profile",
    element: React.lazy(() => import("@/pages/profile")),
  },
  {
    path: "/menu",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/menu")),
  },
  {
    path: "/faq",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/faq")),
  },
  {
    path: "/luck",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/luck")),
  },
  {
    path: "/userluck",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/userLuck")),
  },
  {
    path: "/luckcontent",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/luckContent")),
  },
  {
    path: "/landing",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/landing")),
  },
  {
    path: "/oauth/redirect",
    element: React.lazy(() => import("@/pages/redirect")),
  },
  {
    path: "/dictionary",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/dictionary")),
  },
  {
    path: "/userdictionary",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/nonUserDictionary")),
  },
  {
    path: "/spend",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/spendPattern")),
  },
  {
    path: "/next",
    element: React.lazy(() => import("@/pages/nextSpendPattern")),
  },
  {
    path: "/card",
    layout: DashboardLayout,
    element: React.lazy(() => import("@/pages/cardRecommend")),
  },
  {
    path: "/loading",
    element: React.lazy(() => import("@/pages/loading")),
  },
  // {
  //   path: "/",
  //   element: () => <Navigate replace to="/main" />,
  // },
  {
    path: "/*",
    element: React.lazy(() => import("@/pages/404")),
  },
];

const RenderRoutes = () => {
  return (
    <React.Suspense
      fallback={
        <div>
          <LoadingPage />
        </div>
      }
    >
      <Routes>
        {routes.map((route, i) => {
          const RouteElement = route.element;
          const RouteLayout = route.layout || React.Fragment;
          //   const Guard = route.guard || React.Fragment;

          return (
            <Route
              key={i}
              path={route.path}
              element={
                <RouteLayout>
                  <RouteElement />
                </RouteLayout>
              }
            />
          );
        })}
      </Routes>
    </React.Suspense>
  );
};

export default RenderRoutes;
