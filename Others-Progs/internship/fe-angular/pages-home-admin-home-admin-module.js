(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-home-admin-home-admin-module"],{

/***/ "JpcP":
/*!*******************************************************!*\
  !*** ./src/app/pages/home-admin/home-admin.module.ts ***!
  \*******************************************************/
/*! exports provided: HomeAdminModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeAdminModule", function() { return HomeAdminModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _home_admin_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./home-admin.component */ "W3TW");
/* harmony import */ var src_app_shared__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/shared */ "M0ag");
/* harmony import */ var _home_admin_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./home-admin-routing.module */ "MlBi");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ "fXoL");





class HomeAdminModule {
}
HomeAdminModule.ɵfac = function HomeAdminModule_Factory(t) { return new (t || HomeAdminModule)(); };
HomeAdminModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineNgModule"]({ type: HomeAdminModule });
HomeAdminModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineInjector"]({ imports: [[
            _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
            src_app_shared__WEBPACK_IMPORTED_MODULE_2__["SharedModule"],
            _home_admin_routing_module__WEBPACK_IMPORTED_MODULE_3__["HomeAdminRoutingModule"],
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵsetNgModuleScope"](HomeAdminModule, { declarations: [_home_admin_component__WEBPACK_IMPORTED_MODULE_1__["HomeAdminComponent"]], imports: [_angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
        src_app_shared__WEBPACK_IMPORTED_MODULE_2__["SharedModule"],
        _home_admin_routing_module__WEBPACK_IMPORTED_MODULE_3__["HomeAdminRoutingModule"]] }); })();


/***/ }),

/***/ "MlBi":
/*!***************************************************************!*\
  !*** ./src/app/pages/home-admin/home-admin-routing.module.ts ***!
  \***************************************************************/
/*! exports provided: HomeAdminRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeAdminRoutingModule", function() { return HomeAdminRoutingModule; });
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _home_admin_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./home-admin.component */ "W3TW");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "fXoL");




const routes = [{
        path: '',
        component: _home_admin_component__WEBPACK_IMPORTED_MODULE_1__["HomeAdminComponent"]
    }];
class HomeAdminRoutingModule {
}
HomeAdminRoutingModule.ɵfac = function HomeAdminRoutingModule_Factory(t) { return new (t || HomeAdminRoutingModule)(); };
HomeAdminRoutingModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineNgModule"]({ type: HomeAdminRoutingModule });
HomeAdminRoutingModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵdefineInjector"]({ imports: [[_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"].forChild(routes)], _angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_2__["ɵɵsetNgModuleScope"](HomeAdminRoutingModule, { imports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]], exports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] }); })();


/***/ }),

/***/ "W3TW":
/*!**********************************************************!*\
  !*** ./src/app/pages/home-admin/home-admin.component.ts ***!
  \**********************************************************/
/*! exports provided: HomeAdminComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeAdminComponent", function() { return HomeAdminComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _shared_components_admin_navbar_admin_navbar_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../shared/components/admin-navbar/admin-navbar.component */ "5uIC");
/* harmony import */ var _shared_components_admin_overview_admin_overview_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../shared/components/admin-overview/admin-overview.component */ "70r9");



class HomeAdminComponent {
}
HomeAdminComponent.ɵfac = function HomeAdminComponent_Factory(t) { return new (t || HomeAdminComponent)(); };
HomeAdminComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: HomeAdminComponent, selectors: [["app-home-admin"]], decls: 2, vars: 0, template: function HomeAdminComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](0, "app-admin-navbar");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "app-admin-overview");
    } }, directives: [_shared_components_admin_navbar_admin_navbar_component__WEBPACK_IMPORTED_MODULE_1__["AdminNavbarComponent"], _shared_components_admin_overview_admin_overview_component__WEBPACK_IMPORTED_MODULE_2__["AdminOverviewComponent"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJob21lLWFkbWluLmNvbXBvbmVudC5zY3NzIn0= */"] });


/***/ })

}]);
//# sourceMappingURL=pages-home-admin-home-admin-module.js.map