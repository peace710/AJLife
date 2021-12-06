set nocompatible
set showcmd
set nu
syntax on
nnoremap <esc>^[ <esc>^[
set backspace=2

filetype off
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
Plugin 'VundleVim/Vundle.vim'
Plugin 'valloric/youcompleteme'
call vundle#end()
filetype plugin indent on
