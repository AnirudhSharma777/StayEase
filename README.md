# Stayease

Stayease is a hotel booking system that allows customers to browse hotels, book rooms, and manage bookings. The system also includes features for hotel managers to update hotel details and for administrators to manage hotels.

## Features

- **Public Endpoints**:
  - Browse all available hotels.
  
- **Private Endpoints**:
  - **Customer**:
    - Book a room (only one room per request).
  - **Hotel Manager**:
    - Update hotel details.
    - Cancel bookings.
  - **Administrator**:
    - Create, read, update, and delete hotel details.

## Roles

1. **Customer**: 
   - Can browse hotels and book rooms.
2. **Hotel Manager**: 
   - Can update hotel details and cancel bookings.
3. **Administrator**: 
   - Has full control to create, read, update, and delete hotels.

## Requirements

- Java 17 or higher
- Spring Boot 2.x
- PostgreSQL or any relational database
- JWT for stateless authentication

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/AnirudhSharma777/StayEase.git
   cd stayease
