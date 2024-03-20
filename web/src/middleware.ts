import { type NextRequest, NextResponse } from "next/server";

export function middleware(request: NextRequest) {
  const cookies = request.cookies
  const auth = cookies.get("auth")?.value
  if(auth && auth == "true"){
      return NextResponse.next()
  }
 return NextResponse.redirect(new URL('/login', request.url))
}
export const config = {
  matcher: ["/products", "/transactions"]
}
